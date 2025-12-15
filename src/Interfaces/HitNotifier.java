package Interfaces;

// this interface made for who need to inform that there was a hit
public interface HitNotifier {

    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
