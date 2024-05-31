import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import java.time.{LocalDate, Period}

sealed trait Task {
  def description: String
  def priority: Int
  def completed: Boolean
  def dueDate: Option[LocalDate]
}

case class SimpleTask(description: String, priority: Int, dueDate: Option[LocalDate] = None, completed: Boolean = false) extends Task

case class RecurringTask(description: String, priority: Int, frequency: Int, dueDate: Option[LocalDate] = None, completed: Boolean = false) extends Task {
  def nextDueDate: Option[LocalDate] = {
    dueDate.map { date =>
      val period = Period.ofDays(frequency)
      val nextDate = date.plus(period)
      nextDate
    }
  }
}

object TaskManager {
  private val tasks = ListBuffer[Task]()

  def addTask(task: Task): Unit = {
    tasks += task
    println(s"Task added: ${task.description}")
  }

  def removeTask(index: Int): Unit = {
    if (index >= 0 && index < tasks.size) {
      val task = tasks.remove(index)
      println(s"Task removed: ${task.description}")
    } else {
      println("Invalid task index.")
    }
  }

  def completeTask(index: Int): Unit = {
    if (index >= 0 && index < tasks.size) {
      val task = tasks(index)
      tasks(index) = task match {
        case SimpleTask(description, priority, dueDate, _) => SimpleTask(description, priority, dueDate, completed = true)
        case recurringTask: RecurringTask =>
          RecurringTask(recurringTask.description, recurringTask.priority, recurringTask.frequency, recurringTask.nextDueDate, completed = true)
      }
      println(s"Task marked as completed: ${task.description}")
    } else {
      println("Invalid task index.")
    }
  }

  def viewTasks(): Unit = {
    if (tasks.isEmpty) {
      println("No tasks found.")
    } else {
      println("Tasks:")
      tasks.zipWithIndex.foreach { case (task, index) =>
        val completionStatus = if (task.completed) "[Completed]" else ""
        val dueDate = task.dueDate.map(date => s"(Due: ${date})").getOrElse("")
        println(s"${index + 1}. ${task.description} (Priority: ${task.priority}) $completionStatus $dueDate")
      }
    }
  }

  def sortTasksByPriority(): Unit = {
    tasks.sortBy(_.priority)(Ordering[Int].reverse)
    println("Tasks sorted by priority.")
  }

  def filterTasksByCompletion(completed: Boolean): Unit = {
    val filteredTasks = tasks.filter(_.completed == completed)
    if (filteredTasks.isEmpty) {
      println(s"No ${if (completed) "completed" else "incomplete"} tasks found.")
    } else {
      println(s"${if (completed) "Completed" else "Incomplete"} tasks:")
      filteredTasks.foreach(task => println(s"- ${task.description}"))
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    println("Welcome to the Task Manager!")

    var running = true
    while (running) {
      println("\nPlease select an option:")
      println("1. Add task")
      println("2. Remove task")
      println("3. Complete task")
      println("4. View tasks")
      println("5. Sort tasks by priority")
      println("6. Filter tasks by completion status")
      println("0. Exit")

      val choice = readLine("Enter your choice: ").trim

      choice match {
        case "1" =>
          val description = readLine("Enter task description: ")
          val priority = readLine("Enter task priority (1-5): ").toInt
          val taskType = readLine("Enter task type (simple/recurring): ")
          val task = taskType match {
            case "simple" =>
              val hasDueDate = readLine("Does this task have a due date? (y/n): ").trim.toLowerCase == "y"
              if (hasDueDate) {
                val dueDate = readLine("Enter due date (YYYY-MM-DD): ").trim
                SimpleTask(description, priority, Some(LocalDate.parse(dueDate)))
              } else {
                SimpleTask(description, priority)
              }
            case "recurring" =>
              val frequency = readLine("Enter recurring frequency (in days): ").toInt
              val hasDueDate = readLine("Does this task have a due date? (y/n): ").trim.toLowerCase == "y"
              if (hasDueDate) {
                val dueDate = readLine("Enter due date (YYYY-MM-DD): ").trim
                RecurringTask(description, priority, frequency, Some(LocalDate.parse(dueDate)))
              } else {
                RecurringTask(description, priority, frequency)
              }
            case _ =>
              println("Invalid task type. Defaulting to simple task.")
              SimpleTask(description, priority)
          }
          TaskManager.addTask(task)
        case "2" =>
          val index = readLine("Enter the index of the task to remove: ").trim.toIntOption
          index.foreach(TaskManager.removeTask)
        case "3" =>
          val index = readLine("Enter the index of the task to mark as completed: ").trim.toIntOption
          index.foreach(TaskManager.completeTask)
        case "4" =>
          TaskManager.viewTasks()
        case "5" =>
          TaskManager.sortTasksByPriority()
        case "6" =>
          val completed = readLine("Filter by completed tasks? (true/false): ").trim.toBoolean
          TaskManager.filterTasksByCompletion(completed)
        case "0" =>
          running = false
          println("Goodbye!")
        case _ =>
          println("Invalid choice. Please try again.")
      }
    }
  }
}