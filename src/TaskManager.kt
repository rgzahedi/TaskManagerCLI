import java.io.File

class TaskManager {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1
    private val file = File("tasks.txt")

    fun addTask(name: String, description: String, dueDate: String, priority: String) {
        val task = Task(nextId, name, description, false, dueDate, priority)
        tasks.add(task)
        println("Task added: $name")
    }

    fun removeTask(id: Int) {
        val task = tasks.find { it.id == id }
        if (task != null) {
            tasks.remove(task)
            println("Task removed: ${task.name}")
        } else {
            throw IncorrectID()
        }
    }

    fun updateTaskName(id: Int?, name: String?) {
        val task = tasks.find { it.id == id }
        if (task != null && name != null) {
            task.name = name
            println("Task updated: ${task.name}")
        } else {
            throw IncorrectID()
        }
    }

    fun updateTaskDesc(id: Int?,description: String?) {
        val task = tasks.find { it.id == id }
        if (task != null && description != null) {
            task.description = description
            println("Task updated: ${task.description}")
        } else {
            throw IncorrectID()
        }
    }

    fun updateTaskStatus(id: Int?, status: Boolean?) {
        val task = tasks.find { it.id == id }
        if (task != null && status != null) {
            task.status = status
            println("Task updated: ${task.status}")
        } else {
            throw IncorrectID()
        }
    }

    fun updateTaskDueDate(id: Int?, dueDate: String?) {
        val task = tasks.find { it.id == id }
        if (task != null && dueDate != null) {
            task.dueDate = dueDate
            println("Task updated: ${task.dueDate}")
        } else {
            throw IncorrectID()
        }
    }

    fun updateTaskPriority(id: Int?, priority: String?) {
        val task = tasks.find { it.id == id }
        if (task != null && priority != null) {
            task.priority = priority
            println("Task updated: ${task.priority}")
        } else {
            throw IncorrectID()
        }
    }

    private fun List<Task>.filterCompletion(filterFunction: (Task) -> (Boolean)): List<Task> {
        val resultList = mutableListOf<Task>()
        for (task in this) {
            if (filterFunction(task)) {
                resultList.add(task)
            }
        }
        return resultList
    }

    fun listCompleteTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to show!")
        } else {
            tasks.filterCompletion { it.status }.forEach { task ->
                println("ID: ${task.id}, Name: ${task.name}, Completed: ${task.status}, Due Date: ${task.dueDate}, Priority: ${task.priority}")
            }
        }
    }

    fun listIncompleteTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to show!")
        } else {
            tasks.filterCompletion { !it.status }.forEach { task ->
                println("ID: ${task.id}, Name: ${task.name}, Completed: ${task.status}, Due Date: ${task.dueDate}, Priority: ${task.priority}")
            }
        }
    }

    fun listHighPriorityTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to show!")
        } else {
            tasks.filterCompletion { it.priority == "high" }.forEach { task ->
                println("ID: ${task.id}, Name: ${task.name}, Completed: ${task.status}, Due Date: ${task.dueDate}, Priority: ${task.priority}")
            }
        }
    }

    fun listMedPriorityTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to show!")
        } else {
            tasks.filterCompletion { it.priority == "medium" }.forEach { task ->
                println("ID: ${task.id}, Name: ${task.name}, Completed: ${task.status}, Due Date: ${task.dueDate}, Priority: ${task.priority}")
            }
        }
    }

    fun listLowPriorityTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to show!")
        } else {
            tasks.filterCompletion { it.priority == "low" }.forEach { task ->
                println("ID: ${task.id}, Name: ${task.name}, Completed: ${task.status}, Due Date: ${task.dueDate}, Priority: ${task.priority}")
            }
        }
    }

    fun listTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to show!")
        } else {
            tasks.forEach { task ->
                println("ID: ${task.id}, Name: ${task.name}, Completed: ${task.status}, Due Date: ${task.dueDate}, Priority: ${task.priority}")
            }
        }
    }

    fun loadTasks() {
        if (file.exists()) {
            file.forEachLine { line ->
                val taskData = line.split(",")
                val task = Task(
                    taskData[0].toInt(),
                    taskData[1],
                    taskData[2],
                    taskData[3].toBoolean(),
                    taskData[4],
                    taskData[5]
                )
                nextId++
                tasks.add(task)
            }
        }
    }

    fun saveTasks() {
        file.bufferedWriter().use { writer ->
            tasks.forEach {task ->
                writer.write("${task.id},${task.name},${task.description},${task.status},${task.dueDate},${task.priority}\n")
            }
        }
    }
}

class IncorrectID : Exception("This is an invalid task id!")