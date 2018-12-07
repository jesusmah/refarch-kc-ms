package ibm.labs.kc.serv.ut;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ibm.labs.kc.app.kafka.ContainerPublisher;
import ibm.labs.kc.app.kafka.PositionPublisher;
import ibm.labs.kc.dao.DAOFactory;
import ibm.labs.kc.dao.FleetDAO;
import ibm.labs.kc.model.Fleet;
import ibm.labs.kc.simulator.FleetSimulator;

/**
 * Move only the ships of the north fleet, do not use the publishing of events to kafka
 * @author jerome boyer
 *
 */
public class TestFleetSimulation {
	 @Mock
	 static PositionPublisher positionPublisherMock;
	 @Mock
	 static ContainerPublisher containerPublisherMock;
	 
	 @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	 
	 @Test
	 public void testStartFleet() {
		 FleetSimulator fleetSimulator = new FleetSimulator(positionPublisherMock,containerPublisherMock);
		 FleetDAO dao = DAOFactory.buildOrGetFleetDAO("Fleet.json");
			Fleet f = dao.getFleetByName("KC-FleetNorth");
			fleetSimulator.start(f, .25);
			fleetSimulator.stop(f);
	 }
}
