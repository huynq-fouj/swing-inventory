package swing.inventory.project.components.table;

public interface TableActionEvent {
    public void onView(int row);
    public void onEdit(int row);
    public void onDelete(int row);
}
