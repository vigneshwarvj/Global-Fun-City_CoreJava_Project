


	import java.util.ArrayList;
	import java.util.List;



	import com.google.protobuf.ServiceException;

	import in.fssa.globalfuncity.dao.TicketDAO;
	import in.fssa.globalfuncity.exception.PersistenceException;
	import in.fssa.globalfuncity.model.Ticket;
	import in.fssa.globalfuncity.service.TicketService;

	public class App {
		
		public static void main(String[] args) {
			TicketService ticketservice = new TicketService();
			List<Ticket> ticketList = new ArrayList<>();
			try {
				ticketList = ticketservice.getAllBookedTicketsByUserId(1);
			} catch (ServiceException | PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(Ticket a: ticketList) {
				System.out.println(a);
			}
		}

	
	
	
	}


