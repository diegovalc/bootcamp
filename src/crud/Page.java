
package crud;

/**
 *
 * @author diego
 */
public final class Page {
    private int pageNumber;
    private int pageSize;

    public Page(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffSet(){
        return (pageNumber - 1 ) * pageSize;
    }
    
    
}
