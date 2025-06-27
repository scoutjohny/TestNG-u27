import org.testng.annotations.*;

public class Anotations {

    @Test
    public void test1(){
        System.out.println("Test1 Message");
    }

    @Test
    public void test2(){
        System.out.println("Test2 Message");
    }

    @BeforeClass //Pokreće se jednom pre SVIH testova
    public void beforeClass(){
        System.out.println("Before Class Message");
    }

    @BeforeMethod//Pokreće se jednom pre SVAKOG testa
    public void beforeMethod(){
        System.out.println("Before Test Message");
    }

    @AfterMethod//Pokreće se jednom posle SVAKOG testa
    public void afterMethod(){
        System.out.println("After Test Message");
    }

    @AfterClass//Pokreće se jednom posle SVIH testova
    public void afterClass(){
        System.out.println("After Class Message");
    }



}
