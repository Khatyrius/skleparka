package pl.skleparka.controllerTest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.skleparka.controller.HomeController;

public class HomeControllerTest {
	@Mock
	HomeController homeController;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	RequestDispatcher dispatcher;	
			
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetRedirectToIndex() throws ServletException, IOException {
		//given
        Mockito.when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);

        //when
        HomeController homeController = new HomeController();
        homeController.doGet(request, response);

        //then
        Mockito.verify(request).getRequestDispatcher("index.jsp");
	}
	
	@Test
	public void shouldDoPostBehaveLikeDoGet() throws ServletException, IOException {
		//given
		Mockito.when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);
		 
		//when
	    HomeController homeController = new HomeController();
	    homeController.doPost(request, response);

	    //then
	    Mockito.verify(request).getRequestDispatcher("index.jsp");
	}
}
