package com.genspark.utils;

public class ExceptionUtils
{
	@SuppressWarnings("unused") // Added for clarity’s sake.
	public static class FailedToAddHumanoidException extends Exception {
		public FailedToAddHumanoidException() { super("Failed To Add Humanoid."); }
		public FailedToAddHumanoidException(String message) { super(message); }
		public FailedToAddHumanoidException(String message, Throwable cause) { super(message, cause); }
		public FailedToAddHumanoidException(Throwable cause) { super(cause); }
		}
	
	@SuppressWarnings("unused")
	public static class FailedToMoveToTile extends Exception {
		public FailedToMoveToTile() { super("Failed To Move To Tile."); }
		public FailedToMoveToTile(String message) { super(message); }
		public FailedToMoveToTile(String message, Throwable cause) { super(message, cause); }
		public FailedToMoveToTile(Throwable cause) { super(cause); }
	}
	@SuppressWarnings("unused")
	public static class ExceptionTileInUse extends Exception {
		public ExceptionTileInUse() { super("Failed To Add To Tile."); }
		public ExceptionTileInUse(String message) { super(message); }
		public ExceptionTileInUse(String message, Throwable cause) { super(message, cause); }
		public ExceptionTileInUse(Throwable cause) { super(cause); }
	}
}
