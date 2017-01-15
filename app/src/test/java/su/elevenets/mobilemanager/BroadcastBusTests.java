package su.elevenets.mobilemanager;

import org.junit.Before;
import org.junit.Test;
import rx.functions.Action1;
import su.elevenets.mobilemanager.bus.BroadcastBus;
import su.elevenets.mobilemanager.bus.BroadcastBusImpl;

import static org.mockito.Mockito.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class BroadcastBusTests {

	private BroadcastBus broadcastBus;

	@Before public void before() {
		broadcastBus = new BroadcastBusImpl();
	}

	@SuppressWarnings("unchecked")
	@Test public void postObject() {

		final Action1<Object> handler = mock(Action1.class);
		final Object event = new Object();

		broadcastBus.getObservable().subscribe(handler);
		broadcastBus.post(event);

		verify(handler, times(1)).call(event);
	}

	@SuppressWarnings("unchecked")
	@Test public void postTypedObject() {

		final Action1<Object> handler = mock(Action1.class);
		final Action1<CustomTypeA> handlerA = mock(Action1.class);
		final CustomTypeA eventA = new CustomTypeA();
		final CustomTypeB eventB = new CustomTypeB();

		broadcastBus.getObservable().subscribe(handler);
		broadcastBus.subscribeOn(CustomTypeA.class).subscribe(handlerA);

		broadcastBus.post(eventA);
		broadcastBus.post(eventB);

		verify(handler, times(1)).call(eventA);
		verify(handler, times(1)).call(eventB);
		verify(handlerA, times(1)).call(eventA);
	}

	private static class CustomTypeA {

	}

	private static class CustomTypeB {

	}

}