package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.EmployeeEndpoints;
import entities.Employee;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en_scouse.An;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import utils.Request;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;

public class EmployeesSteps {
    Response response;
    @Given("I perform a GET to the employees endpoint")
    public void getEmployees(){
        response = Request.get(EmployeeEndpoints.GET_EMPLOYEES);
    }

    @And("I verify status code {int} is returned")
    public void verifyStatusCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }

    @And("I verify that the body does not have size {int}")
    public void verifyResponseSize(int size){
        response.then().assertThat().body("size()", not(size));
    }
    // I perform a POST to the create endpoint with the following data

    @And("I perform a POST to the create endpoint with the following data")
    public void postEmployee(DataTable employeeInfo) throws JsonProcessingException {
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName(data.get(0));
        employee.setSalary(data.get(1));
        employee.setAge(data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);
        response = Request.post(EmployeeEndpoints.POST_EMPLOYEE, payload);
    }

    @And("I verify the following data in the body response")
    public void verifyEmployeeResponseData(DataTable employeeInfo){
        List<String> data = employeeInfo.transpose().asList(String.class);
        response.then().assertThat().body("data.name", Matchers.equalTo(data.get(0)));
        response.then().assertThat().body("data.salary", Matchers.equalTo(data.get(1)));
        response.then().assertThat().body("data.age", Matchers.equalTo(data.get(2)));

    }
}
