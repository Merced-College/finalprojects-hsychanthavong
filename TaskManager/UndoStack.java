import java.util.Stack;

public class UndoStack {
    private Stack<Runnable> undoStack = new Stack<>();

    public void pushUndoAction(Runnable action) {
        undoStack.push(action);
    }

    public void undoLastAction() {
        if (!undoStack.isEmpty()) {
            undoStack.pop().run();
            System.out.println("Last action undone.");
        } else {
            System.out.println("No actions to undo.");
        }
    }
}