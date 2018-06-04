package interactor;

public interface OnRequestCompleteListener {
    void onRequestSuccess();
    void onError(String message);
}
