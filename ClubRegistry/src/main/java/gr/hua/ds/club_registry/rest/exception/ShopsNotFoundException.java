package gr.hua.ds.club_registry.rest.exception;

public class ShopsNotFoundException extends RuntimeException{
    public ShopsNotFoundException(){
        super("Shops not found");
    }
}
