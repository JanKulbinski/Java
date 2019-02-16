package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdaper extends ThirdPartyDoor implements Door {

	@Override
	public void open(String code) throws IncorrectDoorCodeException {
		try {
			unlock(code);
			setState(DoorState.OPEN);
		} catch (CannotUnlockDoorException e) {
			throw new IncorrectDoorCodeException();
		} catch (CannotChangeStateOfLockedDoor e) {
			
		}		
	}

	@Override
	public void close() {
		if(getLockStatus() == LockStatus.UNLOCKED) 
			try {
				setState(DoorState.CLOSED);
			} catch (CannotChangeStateOfLockedDoor e) {
				
			}
	}

	@Override
	public boolean isOpen() {
		if( getState() == DoorState.OPEN)
			return true;
		else 
			return false;
	}

	@Override
	public void changeCode(String oldCode, String newCode, String newCodeConfirmation)
			throws IncorrectDoorCodeException, CodeMismatchException {
			
			if(!testCode(oldCode)) {
				throw new IncorrectDoorCodeException();			
			}

			if(newCode.equals(newCodeConfirmation)) {
				try {
					setNewLockCode(newCode);
				} catch (CannotChangeCodeForUnlockedDoor e) {}
			}
			
			else {
				throw new CodeMismatchException();
			}
		
	}

	@Override
	public boolean testCode(String code) {
		try { 
			unlock(code);
		} catch(CannotUnlockDoorException e) {
			return false;
		}
		return true;
	}

}
