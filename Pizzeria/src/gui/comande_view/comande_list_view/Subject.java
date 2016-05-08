package gui.comande_view.comande_list_view;

public interface Subject{
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
    public void setChanged();
}
