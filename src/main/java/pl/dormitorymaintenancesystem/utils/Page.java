package pl.dormitorymaintenancesystem.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page implements Serializable {
    private int numberOfItems;
    private int pageNumber;
    private int pageCount;
    private List<?> content;

    public static Page createPage(int page,int size, List<?> list) {
        Page returnPage = new Page();
        int numberOfPages = (list.size() / size);
        if(list.size()%size>0)
            numberOfPages++;

        returnPage.setNumberOfItems(list.size());
        returnPage.setPageCount(numberOfPages);
        returnPage.setPageNumber(page);

        int beginningIndex = page * size;

        int endIndex = page*size + size <= list.size() ? page*size + size : list.size();

        if(beginningIndex<list.size()) {
            returnPage.setContent(list.subList(beginningIndex,endIndex));
        }

        else {
            returnPage.setContent(null);
        }

        return returnPage;
    }
}
