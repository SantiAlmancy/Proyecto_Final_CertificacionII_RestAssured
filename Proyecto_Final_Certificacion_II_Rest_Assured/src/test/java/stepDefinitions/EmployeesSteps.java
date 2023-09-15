package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.EmployeeEndpoints;
import entities.Employee;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en_scouse.An;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import utils.Request;

import java.util.List;

import static constants.EmployeeEndpoints.*;
import static org.hamcrest.CoreMatchers.not;

public class EmployeesSteps {
    Response response;
    @Given("I perform a GET to the employees endpoint")
    public void getEmployees() throws InterruptedException {
        Thread.sleep(60000);
        response = Request.get(GET_EMPLOYEES);
    }

    @And("I verify status code {int} is returned")
    public void verifyStatusCode(int statusCode) throws InterruptedException {
        response.then().assertThat().statusCode(statusCode);
    }

    @And("I verify that the body does not have size {int}")
    public void verifyResponseSize(int size)
    {
        response.then().assertThat().body("size()", not(size));
    }

    @And("I perform a POST to the create endpoint with the following data")
    public void postEmployee(DataTable employeeInfo) throws JsonProcessingException, InterruptedException
    {
        Thread.sleep(60000);
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName(data.get(0));
        employee.setSalary(data.get(1));
        employee.setAge(data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);
        response = Request.post(POST_EMPLOYEE, payload);
    }

    @And("I verify the following data in the body response")
    public void verifyEmployeeResponseData(DataTable employeeInfo){
        List<String> data = employeeInfo.transpose().asList(String.class);
        response.then().assertThat().body("data.name", Matchers.equalTo(data.get(0)));
        response.then().assertThat().body("data.salary", Matchers.equalTo(data.get(1)));
        response.then().assertThat().body("data.age", Matchers.equalTo(data.get(2)));

    }
    @Given("I perform a DELETE to delete the id {string}")
    public void deleteEmployee(String id) throws InterruptedException
    {
        Thread.sleep(60000);
        this.response = Request.delete(DELETE_EMPLOYEE, id);
    }
    @And("I verify that the employee with id {string} no longer exists")
    public void verifyEmployeeIsDeleted(String id)
    {
        response.then().assertThat().body("status", Matchers.equalTo("success"));
        response.then().assertThat().body("data", Matchers.equalTo(id));
        response.then().assertThat().body("message", Matchers.equalTo("Successfully! Record has been deleted"));
    }
    @And("I verify I get the employee of id {int}")
    public void verifyEmployee(int id)
    {
        response.then().assertThat().body("status", Matchers.equalTo("success"));
        response.then().assertThat().body("data.id", Matchers.equalTo(id));
        response.then().assertThat().body("message", Matchers.equalTo("Successfully! Record has been fetched."));
    }

    @Given("I perform a GET to get the employee of id: {string}")
    public void getEmployeeByID(String id) throws InterruptedException {
        Thread.sleep(60000);
        response = Request.getWithId(GET_EMPLOYEE,id);
    }

    @Given("I perform a PUT to the update a employee of id {string} with the following data:")
    public void putEmployee(String id, DataTable employeeInfo) throws JsonProcessingException, InterruptedException {
        Thread.sleep(60000);
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName(data.get(0));
        employee.setSalary(data.get(1));
        employee.setAge(data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);

        response = Request.put(PUT_EMPLOYEE,id,payload);
    }
    @And("I verify that the body has size 0")
    public void verifyResponseSize0()
    {
        response.then().assertThat().body("data", Matchers.equalTo(null));
    }

}
