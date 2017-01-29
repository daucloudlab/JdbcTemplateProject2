package kz.kaznitu.lessons;


import kz.kaznitu.lessons.interfaces.CustomerDao;
import kz.kaznitu.lessons.models.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProgramm {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml") ;
        CustomerDao customerDao = (CustomerDao)context.getBean("customerDao") ;

        Customer customer1 = new Customer("Арман", 30) ;
        Customer customer2 = new Customer("Айнаш", 25) ;
        Customer customer3 = new Customer(" Ғани", 28) ;

        customerDao.insert(customer1);
        customerDao.insert(customer2);
        customerDao.insert(customer3);

        Customer customer4 = customerDao.findCustomerById(6) ;
        System.out.println(customer4);
    }
}
