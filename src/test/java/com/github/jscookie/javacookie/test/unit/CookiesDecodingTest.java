package com.github.jscookie.javacookie.test.unit;

import com.github.jscookie.javacookie.Cookies;
import com.github.jscookie.javacookie.test.unit.utils.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CookiesDecodingTest extends BaseTest {
  private Cookies cookies;

  @Before
  public void before() {
    cookies = Cookies.initFromServlet(request, response);
  }

  @Test
  public void character_not_allowed_in_name_and_value() {
    Mockito.when(request.getHeader("cookie")).thenReturn("%3B=%3B");
    String actual = cookies.get(";");
    String expected = ";";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void character_with_3_bytes() {
    Mockito.when(request.getHeader("cookie")).thenReturn("c=%E4%BA%AC");
    String actual = cookies.get("c");
    String expected = "京";
    Assert.assertEquals(expected, actual);
  }

  <<<<<<< fix/decoding-two-encoded-chars
  @Test
  public void two_encoded_characters() {
    Mockito.when(request.getHeader("cookie")).thenReturn("c=New%20York%2C%20NY");
    String actual = cookies.get("c");
    String expected = "New York, NY";
    Assert.assertEquals(expected, actual);
  }
  =======
	@Test
	public void character_with_2_bytes() {
		Mockito.when( request.getHeader( "cookie" ) ).thenReturn( "c=%C3%A3" );
		String actual = cookies.get( "c" );
		String expected = "ã";
		Assert.assertEquals( expected, actual );
	}

	@Test
	public void character_with_3_bytes() {
		Mockito.when( request.getHeader( "cookie" ) ).thenReturn( "c=%E4%BA%AC" );
		String actual = cookies.get( "c" );
		String expected = "京";
		Assert.assertEquals( expected, actual );
	}

	@Test
	public void two_encoded_characters() {
		Mockito.when(request.getHeader("cookie")).thenReturn("c=New%20York%2C%20NY");
		String actual = cookies.get("c");
		String expected = "New York, NY";
		Assert.assertEquals(expected, actual);
	}
  >>>>>>> main
}
 