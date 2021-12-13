import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ListPageObjectFactory;
import lib.ui.factories.NewsPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainTestClass extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }
    @Test
    public void testCheckContainsThatSearchFieldHasText(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.checkThatSearchInputContainTextInPlaceHolder("Search Wikipedia");
    }

    @Test
    public void testCheckThatResultsAreGone(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.searchResultMoreOrEqualThanValue(1);
        searchPageObject.closeSearchInput();
        searchPageObject.checkThatSearchResultNotPresent();
    }

    @Test
    public void testContainWordInSearchTitle(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.checkThatAllSearchArticlesContainText("Java");
    }

    @Test
    public void testSaveTwoArticlesInFolder(){

        String folderName = "TestFolder";
        String article_1 = "Java";
        String article_2 = "JavaScript";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.openArticleFromSearchResult(article_1);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            articlePageObject.openArticleOptions();
            articlePageObject.openArticleOptionWithTitle("Add to reading list");
            articlePageObject.acquaintedWithTheInformationBoard();
            articlePageObject.sendReadingListTitle(folderName);
            articlePageObject.clickOKToSaveList();
        }
        else articlePageObject.addArticlesToMySaved();
        articlePageObject.returnToPreviousPage();

        //добавляем вторую статью
        if (Platform.getInstance().isAndroid()){
            searchPageObject.initSearchInput();
            searchPageObject.sendValueInSearchInput("Java");
            searchPageObject.openArticleFromSearchResult(article_2);
            articlePageObject.openArticleOptions();
            articlePageObject.openArticleOptionWithTitle("Add to reading list");
            articlePageObject.addArticleInExistingFolder(folderName);
        }
        else {
            searchPageObject.openArticleFromSearchResult(article_2);
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.returnToPreviousPage();

        NewsPageObject newsPageObject = NewsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            newsPageObject.openSavedLists();
            MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
            myListsPageObject.openList(folderName);
        }
        else{
            searchPageObject.closeSearchInput();
            newsPageObject.openSavedLists();
        }

        ListPageObject listPageObject = ListPageObjectFactory.get(driver);
        // удаляем одну из записей
        if (Platform.getInstance().isAndroid()){
            listPageObject.deleteArticleWithSwipe(article_1);
        }
        else {
            listPageObject.closeSyncWindow();
            listPageObject.openArticle(article_1);
            articlePageObject.setUnsavedArticle();
            articlePageObject.returnToPreviousPage();
        }

        // проверяем, что вторая записб отсталась
        listPageObject.checkThatListContainArticle(article_2);

        // плюс дополнительная проверка на то, что удаленного элемента нету на странице
        listPageObject.checkThatListNotContainArticle(article_1);

        if (Platform.getInstance().isAndroid())
            listPageObject.waitForElementByTitleAndDescription(article_2, "programming language");
        else
            listPageObject.waitForElementByTitleAndDescription(article_2, "High-level programming language");
    }

    @Test
    public void testElementPresentWithoutWait(){

        String articleTitle = "Java";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.openArticleFromSearchResult(articleTitle);

        Assert.assertTrue("Element with title '" + articleTitle + "' not present on the page",
                mainPageObject.assertElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='"+ articleTitle + "']")));
    }

    @Test
    public void testPresentElementsWithTitleAndDescription(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");

        searchPageObject.waitForElementByTitleAndDescription(
                "Java",
                "Island of Indonesia"
        );
        searchPageObject.waitForElementByTitleAndDescription(
                "JavaScript",
                "Programming language"
        );
        searchPageObject.waitForElementByTitleAndDescription(
                "Java (programming language)",
                "Object-oriented programming language"
        );
    }

}
