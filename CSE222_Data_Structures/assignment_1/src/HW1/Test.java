package HW1;

/**
 * Test class
 * @author Türker Tercan
 *
 */
public class Test {

	/**
	 * Testing my homework
	 * 
	 */
	public static void main(String[] args) {
		
		AutomationSystem auto = new AutomationSystem();
		auto.createAdmin();
		auto.createAdmin();
		
		Administrator admin1 = auto.getAdmins()[0];
		Administrator admin2 = auto.getAdmins()[1];

		admin1.createBranch("Yurtiçi Kargo");
		admin2.createBranch("PTT Kargo");
		admin1.createBranch("Aras Kargo");
		
		System.out.println(auto.getBranches()[0]);
		System.out.println(auto.getBranches()[1]);
		System.out.println(auto.getBranches()[2]);
		
		admin1.createBranchEmployee(0, "Türker", "Tercan");
		admin2.createTransportPersonnel(0, "Naime", "Öztunç");
		
		admin1.createBranchEmployee(1, "Çaðla", "Þahin");
		admin2.createTransportPersonnel(1, "Egehan", "Öztürk");
		
		System.out.println(auto.getBranches()[0]);
		System.out.println(auto.getBranches()[1]);
		
		admin1.eraseBranch(2);
		System.out.print("Aras Kargo deleted:\n" + auto.getBranches()[2]);
		
		BranchEmployee emp = auto.getBranches()[0].getWorkers()[0];
		TransportPersonnel person = auto.getBranches()[0].getTransport()[0];
		
		emp.addCustomer("Mert", "Yalçýnöz");
		emp.addCustomer("Cem", "Bozkurt");
		emp.addCustomer("Demirkan", "Demir");
		emp.addCustomer("Joaquin", "Phoenix");
		
		Customer customer = auto.getCustomers()[0];
		
		emp.createShipment(auto.getCustomers()[1], auto.getCustomers()[0], 123);
		emp.createShipment(auto.getCustomers()[2], auto.getCustomers()[3], 321);
		emp.createShipment(auto.getCustomers()[3], auto.getCustomers()[1], 567);
		
		customer.currentStatus(567);
		customer.currentStatus(111);
		customer.currentStatus(123);
		customer.currentStatus(321);
		
		person.shipmentDelivered(1);
		emp.changeShipmentStatus(0, Shipment.status.Shipped);
		
		System.out.println("After shipment updates\n");
		customer.currentStatus(567);
		customer.currentStatus(111);
		customer.currentStatus(123);
		customer.currentStatus(321);
		
		System.out.println("Removing 3rd shipment");
		emp.removeShipment(2);
		customer.currentStatus(567);
		
	}

}
