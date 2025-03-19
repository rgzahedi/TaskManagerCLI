
fun main() {
    println("Welcome to Task Manager CLI!")
    println("Please enter what is your name:")
    val nameInput = readlnOrNull()
    println("Hello $nameInput!")

    val taskManager = TaskManager()
    taskManager.loadTasks()

    while (true) {
        println("\nTask Manager CLI")
        println("1. List tasks")
        println("2. Add task")
        println("3. Remove task")
        println("4. Update task")
        println("5. List Completed Tasks")
        println("6. List Incomplete Tasks")
        println("7. List Low Priority Tasks")
        println("8. List Medium Priority Tasks")
        println("9. List High Priority Tasks")
        println("10. Exit")
        print("Choose an option: ")

        when (readlnOrNull()) {
            "1" -> taskManager.listTasks()
            "2" -> {
                print("Enter task name: ")
                val name = readlnOrNull()
                print("Enter task description: ")
                val description = readlnOrNull()
                print("Enter due date: ")
                val dueDate = readlnOrNull()
                print("Enter the priority: ")
                val priority = readlnOrNull()
                if (priority != "low" && priority != "medium" && priority != "high") {
                    throw InvalidPriority()
                }
                if (name != null && description != null && dueDate != null) {
                    taskManager.addTask(name, description, dueDate, priority)
                }
            }
            "3" -> {
                print("Enter task ID to remove: ")
                val id = readlnOrNull()?.toInt()
                if (id != null) {
                    taskManager.removeTask(id)
                }
            }
            "4" -> {
                while (true) {
                    print("Enter task ID to update: ")
                    val id = readlnOrNull()?.toInt()
                    if (id == 0) {
                        break
                    }
                    print("What would you like to update: ")
                    when (readlnOrNull()) {
                        "Name" -> {
                            print("Enter new name: ")
                            val name = readlnOrNull()
                            taskManager.updateTaskName(id, name)
                        }
                        "Description" -> {
                            print("Enter new description: ")
                            val desc = readlnOrNull()
                            taskManager.updateTaskDesc(id, desc)
                        }
                        "Status" -> {
                            print("Update the status to (true/false)?")
                            val status = readlnOrNull()?.toBoolean()
                            taskManager.updateTaskStatus(id, status)
                        }
                        "Due Date" -> {
                            print("Enter new due date(mm/dd/yyyy): ")
                            val dueDate = readlnOrNull()
                            taskManager.updateTaskDueDate(id, dueDate)
                        }
                        "Priority" -> {
                            print("Update the priority on this: ")
                            val priority = readlnOrNull()
                            if (priority == "low" || priority == "medium" || priority == "high") {
                                taskManager.updateTaskPriority(id, priority)
                            } else {
                                throw InvalidPriority()
                            }
                        }
                        "Done" -> {
                            break
                        }
                        else -> throw InvalidInput()
                    }
                }
            }
            "5" -> taskManager.listCompleteTasks()
            "6" -> taskManager.listIncompleteTasks()
            "7" -> taskManager.listLowPriorityTasks()
            "8" -> taskManager.listMedPriorityTasks()
            "9" -> taskManager.listHighPriorityTasks()
            "10" -> {
                taskManager.saveTasks()
                println("Goodbye!")
                break
            }
            else -> throw InvalidInput()
        }
    }
}

class InvalidInput : Exception("Invalid option, must use something else!")

class InvalidPriority: Exception("Invalid priority naming, must use low, medium, or high!")
