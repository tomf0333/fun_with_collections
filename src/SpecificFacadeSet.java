import java.util.Collection;

/**
 * a specific wrapper made from CollectionFacadeSet to use for OpenHashSet.
 */
public class SpecificFacadeSet extends CollectionFacadeSet {

    public SpecificFacadeSet(java.util.Collection<java.lang.String> collection){
        super(collection);
    }

    public Collection getCollection(){
        return this.collection;
    }
}
