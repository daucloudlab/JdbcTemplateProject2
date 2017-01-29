package kz.kaznitu.lessons.impls;


import kz.kaznitu.lessons.interfaces.CustomerDao;
import kz.kaznitu.lessons.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCustomerDao implements CustomerDao{
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource) ;
    }

    public void insert(Customer customer) {
        String sql = "INSERT INTO customer (name, age) VALUES (?, ?)" ;
        jdbcTemplate.update(sql, new Object[]{customer.getName(), customer.getAge()}) ;

    }

    public Customer findCustomerById(final int custId) {
        String sql = "SELECT * FROM customer WHERE cust_id = ?" ;
        return jdbcTemplate.query(sql, new Object[]{custId}, new ResultSetExtractor<Customer>() {
            public Customer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Customer customer = null ;
                while (resultSet.next()){
                    customer = new Customer() ;
                    customer.setCustId(resultSet.getInt("cust_id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setAge(resultSet.getInt("age"));
                }
                return customer;
            }
        }) ;
    }
}
