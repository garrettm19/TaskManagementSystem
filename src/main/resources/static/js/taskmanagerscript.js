document.addEventListener('DOMContentLoaded', (event) => {
    var dragSrcEl = null;

    function handleDragStart(e) {
        console.log('Drag started');
        this.style.opacity = '0.1';
        this.style.border = '3px dashed #c4cad3';

        dragSrcEl = this;

        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData('text/html', this.innerHTML);
    }

    function handleDragOver(e) {
        console.log('Drag over');
        if (e.preventDefault) {
            e.preventDefault();
        }

        e.dataTransfer.dropEffect = 'move';

        return false;
    }

    function handleDragEnter(e) {
        console.log('Drag enter');
        this.classList.add('task-hover');
    }

    function handleDragLeave(e) {
        console.log('Drag leave');
        this.classList.remove('task-hover');
    }

    function handleDrop(e) {
        console.log('Dropped');
        if (e.stopPropagation) {
            e.stopPropagation();
        }

        if (dragSrcEl != this) {
            dragSrcEl.innerHTML = this.innerHTML;
            this.innerHTML = e.dataTransfer.getData('text/html');

            // Get the task ID from the dragged element (you may need to add an attribute for task ID in your HTML)
            const taskId = dragSrcEl.getAttribute('data-task-id');
            console.log('Task ID:', taskId);

            // Determine the new state based on the drop target (section)
            const newState = this.querySelector('.project-column-heading__title').textContent;
            console.log('New State:', newState);

            // Send a POST request to update the task's state
            fetch(`/api/task/updateTaskPosition/${taskId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newState)
            })
                .then(response => {
                    if (response.ok) {
                        // Task state updated successfully, you can update the UI or reload tasks if needed
                        fetchTasks();
                    } else if (response.status === 404) {
                        // Handle the case where the task is not found (Not Found status code)
                        console.error('Task not found with ID:', taskId);
                    } else {
                        // Handle other errors here
                        console.error('Failed to update task state:', response.statusText);
                    }
                })
                .catch(error => {
                    console.error('Error updating task state:', error);
                });
        }

        return false;
    }

    function handleDragEnd(e) {
        console.log('Drag end');
        this.style.opacity = '1';
        this.style.border = 0;

        items.forEach(function (item) {
            item.classList.remove('task-hover');
        });
    }

    let items = document.querySelectorAll('.task');
    items.forEach(function(item) {
        item.addEventListener('dragstart', handleDragStart, false);
        item.addEventListener('dragenter', handleDragEnter, false);
        item.addEventListener('dragover', handleDragOver, false);
        item.addEventListener('dragleave', handleDragLeave, false);
        item.addEventListener('drop', handleDrop, false);
        item.addEventListener('dragend', handleDragEnd, false);
    });

    // Function to fetch tasks from the backend
    function fetchTasks() {
        console.log('Fetching tasks...');
        fetch("/api/task/") // Update the API endpoint for fetching tasks
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error fetching tasks: ${response.status}`);
                }
                return response.json();
            })
            .then(tasks => {
                // Call a function to update the UI with the fetched tasks
                updateTaskUI(tasks);
            })
            .catch(error => {
                console.error('Error fetching tasks:', error.message);
            });
    }

    // Function to update the UI with tasks
    function updateTaskUI(tasks) {
        // Group tasks by their state
        const tasksByState = {
            idea: [],
            inprogress: [],
            needsreview: [],
            completed: [],
        };
        tasks.forEach(task => {
            tasksByState[task.state].push(task);
        });

        // Update the UI with tasks in each section
        Object.keys(tasksByState).forEach(state => {
            const tasksForState = tasksByState[state];
            const sectionElement = document.getElementById(`${state}-section`);

            if (sectionElement) {
                // Clear existing tasks in the section
                sectionElement.innerHTML = '';

                // Add fetched tasks to the section
                tasksForState.forEach(task => {
                    const taskElement = createTaskElement(task);
                    sectionElement.appendChild(taskElement);
                });
            }
        });
    }

    // Function to format the created_at column as "Month Day"
    function formatDate(dateString) {
        if (!dateString) {
            return 'N/A'; // Return a placeholder if the date is missing
        }

        const inputDate = new Date(dateString);

        if (isNaN(inputDate)) {
            return 'Invalid Date'; // Return an error message for an invalid date
        }

        const months = [
            'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
        ];

        const month = months[inputDate.getMonth()];
        const day = inputDate.getDate();

        return `${month} ${day}`;
    }

    // Function to generate HTML elements for individual tasks
    function createTaskElement(task) {
        const taskElement = document.createElement('div');
        taskElement.classList.add('task');
        taskElement.draggable = true;

        // Capitalize the first letter of task.tag for display
        let capitalizedTag = ''; // Initialize as an empty string
        if (task.tag) {
            capitalizedTag = task.tag.charAt(0).toUpperCase() + task.tag.slice(1);
        }


        // Format the created_at column
        const formattedCreatedAt = formatDate(task.createdAt);

        taskElement.innerHTML = `
    <div class='task__tags'><span class='task__tag task__tag--${task.tag}'>${capitalizedTag}</span><button class='task__options'><i class="fas fa-ellipsis-h"></i></button></div>
    <p>${task.title}</p>
    <div class='task__stats'>
        <span><time datetime="${task.date}"><i class="fas fa-flag"></i>${formattedCreatedAt}</time></span>
        <span><i class="fas fa-comment"></i>${task.comments}</span>
        <span><i class="fas fa-paperclip"></i>${task.attachments}</span>
        <span class='task__owner'>${task.owner}</span>
    </div>
    `;
        // Add event listeners for drag-and-drop
        taskElement.addEventListener('dragstart', handleDragStart, false);
        taskElement.addEventListener('dragenter', handleDragEnter, false);
        taskElement.addEventListener('dragover', handleDragOver, false);
        taskElement.addEventListener('dragleave', handleDragLeave, false);
        taskElement.addEventListener('drop', handleDrop, false);
        taskElement.addEventListener('dragend', handleDragEnd, false);

        return taskElement;
    }

    // Fetch tasks when the page loads
    fetchTasks();
});
