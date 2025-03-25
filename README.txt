STUDENT INFORMATION
	Name: Ben Turner-Theijsmeijer
	student ID: 1152536
	
ASSIGNMENT INFORMATION
	Assignment 3
	
COURSE
	CIS*2430 Object Oriented Programming; Fall 2021

DATE OF LAST REVISION
	November 29, 2021

PROBLEM TO BE SOLVED / PURPOSE OF PROGRAM
	The general problem is that investors need a simple and easy way to manage their investment all in one place: 
    An ePortfolio if you will. Therefore, the purpose of this program is to make an easy way to buy investments, 
    sell investments, update investment prices, calculate the theoretical gain on owned investment, and search 
    for relevant investments.

ASSUMPTIONS AND LIMITATIONS
	The assumptions and limitations on my program include the following:
		The program can only operate on stock and mutual fund investments.
		The program cannot search for investments that are not currently owned by the user.
		Users must manually update investment prices when normally this would be done automatically.
		Users cannot search for investments based on book value or quantity owned.

USER GUIDE
	To build the ePortfolio program the user must run the following
	javac *.java 
		From withing the ePortfolio package.
	To run the program the user must run the following
	java bturnert_a3.ePortfolio.Portfolio
	or
	java bturnert_a3.ePortfolio.Portfolio FILENAME (if you wish to load information from a previously created file, where FILENAME is the name of the file you wish to have read)
		From outside the file bturnert_a3.

	Once in the program the user can test the functionality of the different operations 
    by selecting the operation they would like to perform in the menu bar.
	For example when the window appears selecting an option from the drop down menu bar will select that action.

TEST PLAN
	Testing the program functions.
		Clicking on the JMenu bar while on the welcome page brings up the menu with all of the options as clickable items correctly.

		Testing Buy:
			Selecting the Buy operation in the menu bar properly brings up the buy page with all of the components, including the text fields, message TextArea and buttons.
			Testing JComboBox:
				When the buy page is loaded it is always set  to stock by default.
				Clicking on the combo box brings up all options correctly, Stock and MutualFund.
				Selecting one of the combo box options updates the combo box to that option correctly.
			Testing TextFields:
				TextFields can be written in.
				TextFields will not allow for an investment to be bought if a value is incorrectly written in them.
				Example, clicking buy when no fields are filled out:
					returns a message to the message TextArea saying that buy was unsuccessful and listing the first error it encountered.
					In this case that message being: "Error: Symbol field cannot be left blank Buy aborted, please address Errors and try again".
			Testing Buttons:
				Clicking on the reset button will clear all TextFields in the buy pannel.
				Clicking Buy will attempt to buy an investment based on the values in the TextFields and will only be successful if the values were entered correctly.
				Example, clicking Buy when type = "Stock", symbol = "test", name = "example.co", quantity = "10", and price = "15.99":
					Results in the successful buy of a stock investment with the symbol "test", name "example.co", quantity of "10" and a price of "15.99"
					and outputs the followung to the messages TextArea: "Buy successful for new Investment"
				additionally if a stock with the matching type, symbol, and name is entered after the original stock is purchased it updates the exisitng stock isntead of making a new one.
					if stock "test" exists then clicking Buy when type = "Stock", symbol = "test", name = "example.co", quantity = "50", and price = "20"
					results in buying additional stock with the symbol "test" and outputting the following to the message TextArea.
					"Buy successful for exisitng Investment"
				additionally if a when trying to buy additional stock of "test" the type was set to MutualFund the buy would abort saying the following: 
					"Buy aborted, different Investment types cannot have the same symbol Please edit your investment again"

		Testing Sell:
			Selecting the Sell operation in the menu bar properly brings up the sell page with all of the components, including the text fields, message TextArea and buttons.
			If when Sell is selected there are no investments currently in the Portfolio then the Sell button is disabled and the message TextArea states the following:
				"Oops: Cannot sell any investments at this time As no investments are currently in the Portfolio".
			Testing TextFields:
				TextFields can be written in.
				TextFields will not allow for an investment to be sold if a value is incorrectly written in them.
				Example, clicking sell when no fields are filled out:
					returns a message to the message TextArea saying that sell was unsuccessful and listing the first error it encountered.
					in this case that message being: "Error: Symbol field cannot be left blank Sell aborted, please address Errors and try again".
			Testing Buttons:
				Clicking on the reset button will clear all TextFields in the sell pannel.
				Clicking sell will attempt to sell an investment based on the values in the TextFields and will only be successful if the values were entered correctly.
				Example (after buying the stock "test" with a qunaity of "10" and price of "10" previously mentioned in the Buy example), clicking sell when symbol = "test", quantity = "10", and price = "15":
					Results in the successful sell of a stock investment with the symbol "test", name "example.co", quantity of "10" at a price of "15"
					and outputs the followung to the messages TextArea: "Sell successful, all of investment sold for $30.02"
				additionally if the stock was not fully sold but instead the quantity was "5" so it was only partially sold and everything else was the same:
					results in selling only part of the investment of type "test" and outputting the following to the message TextArea.
					"Sell successful, part of investment sold for $65.01"

		Testing Update:
			Selecting the Update operation in the menu bar properly brings up the Update page with all of the components, including the text fields, message TextArea and buttons.
			If when Update is selected there are no investments currently in the Portfolio then the Next and Previous buttons are disabled and the message TextArea states the following:
				"Oops: Cannot update any investments at this time As no investments are currently in the Portfolio".
			Always starts the user on the first Investment in the list, with the previous button disabled.	
			Testing TextFields:
				price Field can be written in.
				Symbol and Name Fields Are not editable.
				Price field will not allow for an investment to be updated if a value is incorrectly written in it.
				Example, bringing up the Update window after buying an investment of Type = "Stock", Symbol = "a", Name = "a", Quantity = "10", Price = "20.0" (with only this investment in the ArrayList).
					Previous and Next are disabled as it is the only investment in the Portfolio.
					The currently selected Investment in the ArrayList is show in the message TextArea. (in this case being stock "a")
					The Symbol, Name, and Price are displayed in their respective TextFields for the selected Investment.
					Changing the Price of "a" from 20.0 to 30.0 and pressing Save would result in the price for "a" being updated in the ArrayList and thus being updated in the message TextArea.
				additionally if there was another Investment then the Next button would be pressable and doing so would bring up the next investment in the ArrayList and allow the user to update it's price.

			Testing Buttons:
				The Previous button is disabled if the user is at the first Investment in the arraylist and cannot go back farther.
				the Next button is disabled if the user is at the last Investment in the ArrayLsit and cannot go forward farther.
				If there was another Investment after the current one then the Next button would be pressable and doing so would bring up the next investment in the ArrayList and allow the user to update it's price.
				If there was another Investment before the current one then the Previous button would be pressable and doing so would bring up the previous invest in the ArrayList and allow the user to update it's price.
				Clicking Save will attempt to Update the price of an exisitng Investment based on the values in the Price field and will only be successful if the values were entered correctly.


		Testing GetGain:
			Selecting the getGian operation in the menu bar properly brings up the GetGain page with all of the components, including the text field, and individual gains TextArea.
			If when GetGain is selected there are no investments currently in the Portfolio then the individual gains TextArea states the following:
				"Oops: Cannot get gain on investments at this time As no investments are currently in the Portfolio".
			Testing TextFields:
				when a buy, sell, or update action occurs the investments in the get gain window update as well as the theoretical total gain.
				Example, bringing up the GetGain window after buying an investment of Type = "Stock", Symbol = "a", Name = "a", Quantity = "10", Price = "20.0".
					individual gains TextArea will have the information for the investment along with its book value and indiviual gain: "Gain on Investment = $-19.98".
					Total gain will be set to the gain of the only investment, in this case stock "a" and total gain = "$-19.98".
				Alternatively if after buying Stock "a" the user updates the price of "a" from $20 to $30 the get gain pannel will be updated as well with the new total gain equalling: "$80.02".

		Testing Search:
			Selecting the Search operation in the menu bar properly brings up the Search page with all of the components, including the text field, and search results TextArea.
			If when Search is selected there are no investments currently in the Portfolio then the Search button is dissabled and the Search Results TextArea states the following:
				"Oops: Cannot search for any investments at this time As no investments are currently in the Portfolio"
			Testing TextFields:
				textFields can be left blank for search on all Investments.
				Price and Quantity cannot be given anything except number values without the search failing and returning an error message such as: "Search aborted, please address Errors and try again"
				Example, clicking Search when no fields are filled out:
					Retuns all investments currenltly in Portfolio.
				Example, clicking Search with symbol and Name key Words filled out:
					Returns all investments currently in portfolio matching symbol and key words searched for.
			Testing Buttons:
				Clicking on the reset button will clear all TextFields in the Search pannel.
				Clicking Search will attempt to search all investments matching search parameters entered by the user, only performing the search if there are no issues with the entered searh parameters.
				Example, clicking Search (with no parameters) after buying an investment of Type = "Stock", Symbol = "a", Name = "a", Quantity = "10", Price = "20.0" (with only this investment in the ArrayList).
					Prints out the investment in the search results TextArea.
				Alternatively if the Search parameter Symbol was set to "b" when Search was clicked then nothing would be shown in the Search Results TextArea.




POSSIBLE IMPROVEMENTS
	Decrease the length of the program's code by implementing better more concise methods. 
	Add additional functionality.
	Fix some edge cases that could possibly cause program failure.
    Improving uppon current search method.
	Add way to save to file when X is clicked isntead of quit.
	For whatever reason the program does not work when price is over 999, it seems to have to do with the program automatically adding a comma after it hits larger values
	but i have tried pulling the comma out an it does not work. When i print the price there is no comma but when the exception occurs there is.
