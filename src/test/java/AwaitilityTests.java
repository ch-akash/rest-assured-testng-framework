import apis.GetBookingApi;
import org.awaitility.Awaitility;
import org.testng.annotations.Test;

import java.time.Duration;

public class AwaitilityTests {

    /**
     *
     */

    @Test(description = "Basic HTTP Status check with retrying assertions")
    public void validateStatusCodeForGetBookingByIdApi() {
        var getBookingApi = new GetBookingApi();
        Awaitility.await()
                  .and().atMost(Duration.ofSeconds(5))
                  .and().with().alias("Waiting for get booking status code to be 201")
                  .untilAsserted(() -> {
                      getBookingApi.getBookingById(20).then()
                                   .assertThat().statusCode(404);
                  });
    }

}
