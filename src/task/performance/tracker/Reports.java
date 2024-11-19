
package task.performance.tracker;


import java.util.Scanner;

public class Reports {
     public void empReports(){
         
       Scanner sc = new Scanner(System.in);
        
       String res;
        do {
            System.out.print("\n-----------------------------");
           System.out.print("\n| WELCOME TO REPORT SECTION |");
           System.out.println("\n-----------------------------");
            System.out.println("1. VIEW SPECIFIC REPORT");
            System.out.println("2. VIEW GENERAL REPORT");
            System.out.println("3. VIEW COMPLETED TASKS OF EMPLOYEE");
            System.out.println("4. EXIT");

            int opt = -1;
            boolean validchoice = false;

            while (!validchoice) {
                System.out.print("Enter option: ");
                if (sc.hasNextInt()) {
                    opt = sc.nextInt();
                    if (opt >= 1 && opt <= 4) {
                        validchoice = true;
                    } else {
                        System.out.println("Wrong input! Maximum is 4");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();
                }
            }
            Reports rp = new Reports();
            
            switch (opt) {
                case 1:
                    
                
                    rp.viewSpecificReport();
                 
                    break;

                case 2:
               
                    rp.viewGeneralReport();
                    break;

                case 3:
                    
                
                    rp.empCompletedTasks();
                    
                    break;
                    
                case 4:
                    
                    System.out.println("Exiting to main menu...");
                    break;
            }

            System.out.print("Do you want to continue? yes/no: ");
            res = sc.next();
        } while (res.equalsIgnoreCase("yes"));
    }
     
      public void viewSpecificReport() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.println("Choose the type of report:");
        System.out.println("1. View task report by Task ID");
        System.out.println("2. View task report by Employee ID");
        int choice = -1;
    boolean validChoice = false;

    while (!validChoice) {
        System.out.print("Enter option: ");
        if (sc.hasNextInt()) {
            choice = sc.nextInt();
            if (choice == 1 || choice == 2) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        } else {
            System.out.println("Invalid input! Please enter a number.");
            sc.next();
        }
    }

    if (choice == 1) {
        System.out.print("Enter Task ID: ");
        int taskId = sc.nextInt();
        
        String taskQuery = "SELECT task_id, task_name, task_description, task_assigned, emp_lname, task_deadline, task_status " +
                           "FROM tbl_task " +
                           "JOIN tbl_employee ON task_assigned = emp_id " +
                           "WHERE task_id = " + taskId;
        String[] taskHeaders = {"Task ID", "Task Name", "Description", "Assigned Employee ID", "Employee Name", "Deadline", "Status"};
        String[] taskColumns = {"task_id", "task_name", "task_description", "task_assigned", "emp_lname", "task_deadline", "task_status"};
        conf.viewRecords(taskQuery, taskHeaders, taskColumns);
        
    } else if (choice == 2) {
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();
       
        String empQuery = "SELECT task_id, task_name, task_description, task_assigned, emp_lname, task_deadline, task_status " +
                          "FROM tbl_task " +
                          "JOIN tbl_employee ON task_assigned = emp_id " +
                          "WHERE task_assigned = " + empId;
        String[] empHeaders = {"Task ID", "Task Name", "Description", "Assigned Employee ID", "Employee Name", "Deadline", "Status"};
        String[] empColumns = {"task_id", "task_name", "task_description", "task_assigned", "emp_lname", "task_deadline", "task_status"};
        conf.viewRecords(empQuery, empHeaders, empColumns);
    }
      }
   
    public void viewGeneralReport() {
        config conf = new config();

        
        String genQuery = "SELECT task_id, task_name, task_description, task_assigned, emp_lname, task_deadline, task_status " +
                              "FROM tbl_task  " +
                              "JOIN tbl_employee  ON task_assigned = emp_id";
        String[] genHeaders = {"Task ID", "Task Name", "Description", "Assigned Employee ID", "Employee Name", "Deadline", "Status"};
        String[] genColumns = {"task_id", "task_name", "task_description", "task_assigned", "emp_lname", "task_deadline", "task_status"};
        conf.viewRecords(genQuery, genHeaders, genColumns);
    }
    public void empCompletedTasks() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Employee ID: ");
    int empId = sc.nextInt();

   
    String completedTasksQuery = "SELECT emp_id, emp_lname, COUNT (task_id) AS completed_tasks " +
                                  "FROM tbl_employee  " +
                                  "LEFT JOIN tbl_task  ON emp_id = task_assigned " +
                                  "WHERE emp_id = " + empId + " AND task_status = 'Completed' " +
                                  "GROUP BY emp_id, emp_lname";

    config conf = new config();
    String[] headers = {"Employee ID", "Employee Last Name", "Completed Tasks Count"};
    String[] columns = {"emp_id", "emp_lname", "completed_tasks"};

    conf.viewRecords(completedTasksQuery, headers, columns);
}

}
    

      
       
