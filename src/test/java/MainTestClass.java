import lib.CoreTestCase;
import lib.ui.*;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class MainTestClass extends CoreTestCase {

    private MainPageObject mainPageObject;
    private static final String defaultSearchArticleLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }
    @Test
    public void testCheckContainsThatSearchFieldHasText(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.checkThatSearchInputContainTextInPlaceHolder("Search Wikipedia");
    }

    @Test
    public void testCheckThatResultsAreGone(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.searchResultMoreOrEqualThanValue(1);
        searchPageObject.closeSearchInput();
        searchPageObject.checkThatSearchResultNotPresent();
    }

    @Test
    public void testContainWordInSearchTitle(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.checkThatAllSearchArticlesContainText("Java");
    }

    @Test
    public void testSaveTwoArticlesInFolder(){

        String folderName = "TestFolder";
        String article_1 = "Java";
        String article_2 = "JavaScript";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.openArticleFromSearchResult(article_1);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.openArticleOptions();
        articlePageObject.openArticleOptionWithTitle("Add to reading list");
        articlePageObject.acquaintedWithTheInformationBoard();
        articlePageObject.sendReadingListTitle(folderName);
        articlePageObject.clickOKToSaveList();
        articlePageObject.returnToPreviousPage();

        //добавляем вторую статью
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.openArticleFromSearchResult(article_2);

        articlePageObject.openArticleOptions();
        articlePageObject.openArticleOptionWithTitle("Add to reading list");
        articlePageObject.addArticleInExistingFolder(folderName);
        articlePageObject.returnToPreviousPage();

        NewsPageObject newsPageObject = new NewsPageObject(driver);
        newsPageObject.openSavedLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openList(folderName);

        ListPageObject listPageObject = new ListPageObject(driver);
        // удаляем одну из записей
        listPageObject.deleteArticleWithSwipe(article_1);
        // проверяем, что вторая записб отсталась
        listPageObject.checkThatListContainArticle(article_2);

        // плюс дополнительная проверка на то, что удаленного элемента нету на странице
        listPageObject.checkThatListNotContainArticle(article_1);

        listPageObject.openArticle(article_2);

        // добавялем ожидание того, что прогрузилось название статьи и заодно сохраняем элемент для извлечения его текста в след шаге
        WebElement articleTitle = articlePageObject.getArticleTitleElement(article_2);

        Assert.assertEquals(
                "Expected article '" + article_2 + "' not equals with actual '" + articleTitle.getText() + "'",
                article_2,
                articleTitle.getText()
                );
    }

    @Test
    public void testElementPresentWithoutWait(){

        String articleTitle = "Java";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.sendValueInSearchInput("Java");
        searchPageObject.openArticleFromSearchResult(articleTitle);

        Assert.assertTrue("Element with title '" + articleTitle + "' not present on the page",
                mainPageObject.assertElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='"+ articleTitle + "']")));
    }

    @Test
    public void testPresentElementsWithTitleAndDescription(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);
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
