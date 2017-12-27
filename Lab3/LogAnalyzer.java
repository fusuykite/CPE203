import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class LogAnalyzer {
   //constants to be used when pulling data out of input
   //leave these here and refer to them to pull out values
   private static final String START_TAG = "START";
   private static final int START_NUM_FIELDS = 3;
   private static final int START_SESSION_ID = 1;
   private static final int START_CUSTOMER_ID = 2;
   private static final String BUY_TAG = "BUY";
   private static final int BUY_NUM_FIELDS = 5;
   private static final int BUY_SESSION_ID = 1;
   private static final int BUY_PRODUCT_ID = 2;
   private static final int BUY_PRICE = 3;
   private static final int BUY_QUANTITY = 4;
   private static final String VIEW_TAG = "VIEW";
   private static final int VIEW_NUM_FIELDS = 4;
   private static final int VIEW_SESSION_ID = 1;
   private static final int VIEW_PRODUCT_ID = 2;
   private static final int VIEW_PRICE = 3;
   private static final String END_TAG = "END";
   private static final int END_NUM_FIELDS = 2;
   private static final int END_SESSION_ID = 1;

   public static void main(String[] args)
   {
      /* Map from a customer id to a list of session ids associated with
       * that customer.
       */
      final Map<String, List<String>> sessionsFromCustomer = new HashMap<>();
      Map<String, List<View>> viewsFromSessions = new HashMap<>();
      Map<String, List<Buy>> buysFromSessions = new HashMap<>();


      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */

      final String filename = getFilename(args);

      try
      {
         populateDataStructures(filename, sessionsFromCustomer,
                 viewsFromSessions,
                 buysFromSessions
         );
         printStatistics(
                 sessionsFromCustomer,
                 viewsFromSessions,
                 buysFromSessions
         );
      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }

      //printOutExample(sessionsFromCustomer, viewsFromSessions, buysFromSessions);
   }

   //called from main - mostly just pass through important data structures
   private static void populateDataStructures(
           final String filename,
           final Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<View>> viewsFromSessions,
           Map<String, List<Buy>> buysFromSessions

      /* add parameters as needed */
   )
           throws FileNotFoundException
   {
      try (Scanner input = new Scanner(new File(filename)))
      {
         processFile(input, sessionsFromCustomer,
                 viewsFromSessions, buysFromSessions
            /* add arguments as needed */ );
      }
   }
   //called in populateDataStructures
   private static void processFile(
           final Scanner input,
           final Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<View>> viewsFromSessions,
           Map<String, List<Buy>> buysFromSessions
      /* add parameters as needed */
   )
   {
      while (input.hasNextLine())
      {
         processLine(input.nextLine(), sessionsFromCustomer,
                 viewsFromSessions, buysFromSessions
            /* add arguments as needed */ );
      }
   }
   //this is called by processFile below - its main purpose is
   //to process the data using the methods you write above
   private static void processLine(
           final String line,
           final Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<View>> viewsForSessions,
           Map<String, List<Buy>> buysForSessions
      /* add parameters as needed */
   ) {
      final String[] words = line.split("\\h");

      if (words.length == 0) {
         return;
      }

      switch (words[0]) {
         case START_TAG:
            processStartEntry(words, sessionsFromCustomer);
            break;
         case VIEW_TAG:
            processViewEntry(words, viewsForSessions );
            break;
         case BUY_TAG:
            processBuyEntry(words, buysForSessions);
            break;
         case END_TAG:
            processEndEntry(words);
            break;
      }
   }



   //a good example of what you will need to do next
   //creates a map of sessions to customer ids
   private static void processStartEntry(
           final String[] words,
           final Map<String, List<String>> sessionsFromCustomer) {
      if (words.length != START_NUM_FIELDS) {
         return;
      }

      //check if there already is a list entry in the map
      //for this customer, if not create one
      List<String> sessions = sessionsFromCustomer.get(words[START_CUSTOMER_ID]);
      if (sessions == null) {
         sessions = new LinkedList<>();
         sessionsFromCustomer.put(words[START_CUSTOMER_ID], sessions);

      }

      //now that we know there is a list, add the current session
      sessions.add(words[START_SESSION_ID]);
   }

   //similar to processStartEntry, should store relevant view
   //data in a map - model on processStartEntry, but store
   //your data to represent a view in the map (not a list of strings)
   private static void processViewEntry(final String[] words,
                                        Map<String, List<View>> viewsFromSessions
      /* add parameters as needed */
   ) {
      if (words.length != VIEW_NUM_FIELDS) {
         return;
      }

      // If a view list does not exist for the current session, create it and add the entry with a new LinkedList
      // as the value.
      String sessionId = words[VIEW_SESSION_ID];
      viewsFromSessions.putIfAbsent(sessionId, new LinkedList<>());

      // Grabs the sessionId, productId, and productPrice, and stores it in a View object,
      // which is then stored into the viewsFromSessions List<View> for
      // the respective String (sessionId).

      String productId = words[VIEW_PRODUCT_ID];
      int productPrice = parseInt(words[VIEW_PRICE]);
      viewsFromSessions.get(sessionId).add(new View(productId, productPrice));

   }

   //similar to processStartEntry, should store relevant purchases
   //data in a map - model on processStartEntry, but store
   //your data to represent a purchase in the map (not a list of strings)
   private static void processBuyEntry(
           final String[] words,
           Map<String, List<Buy>> buysFromSessions
   ) {
      if (words.length != BUY_NUM_FIELDS) {
         return;
      }

      // If a buy list does not already exist for the current session,
      // add an entry to the map with new LinkedList as the value.

      String sessionId = words[BUY_SESSION_ID];

      buysFromSessions.putIfAbsent(sessionId, new LinkedList<>());
      // Grabs all the data and encapsulates it in a Buy object,
      // and stores said object into the buyList.

      String productId = words[BUY_PRODUCT_ID];
      int productPrice = Integer.parseInt(words[BUY_PRICE]);
      int productQuantity = Integer.parseInt(words[BUY_QUANTITY]);

      buysFromSessions.get(sessionId).add(new Buy(productId, productPrice, productQuantity));

   }

   private static void processEndEntry(final String[] words) {
      if (words.length != END_NUM_FIELDS) {
         return;
      }
   }


   // Obtains the average views in sessions without purchases.
   private static void printAverageViewsWithoutPurchase(
           Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<View>> viewsFromSessions,
           Map<String, List<Buy>> buysFromSessions) {

      // Defines the list for all sessions without purchases.
      List<String> sessionNoPurchaseList = new LinkedList<>();

      // Grabs all the sessions with purchases. Then, constructs a list of sessions without purchases.
      List<String> sessionPurchaseList = getSessionsWithPurchases(sessionsFromCustomer,
              buysFromSessions);

      for (List<String> sessionList: sessionsFromCustomer.values()) {
         for (String session: sessionList) {
            if (!sessionPurchaseList.contains(session)) {
               sessionNoPurchaseList.add(session);
            }
         }

      }
      // Gets total views for the list of no-purchase sessions.
      //System.out.println(sessionNoPurchaseList.toString());
      int totViews = getTotViews(sessionNoPurchaseList, viewsFromSessions);

      // Returns average views.
      System.out.printf("Average Views without Purchase: %.1f\n",
              totViews / (double) sessionNoPurchaseList.size());
   }

   // Counts total views from a list of sessions.
   private static int getTotViews(List<String> sessionList, Map<String, List<View>> viewMap) {
      int views = 0;
      for (String sessionId: sessionList) {
         List<View> currentViewSession = viewMap.get(sessionId);
         // Must check if the current view is not null. Sometimes a session with no purchases also has no views.
         if (currentViewSession != null) {
            views += viewMap.get(sessionId).size();
         }
      }
      return views;
   }

   //write this after you have figured out how to store your data
   //make sure that you understand the problem
   private static void printSessionPriceDifference(
           Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<View>> viewsFromSessions,
           Map<String, List<Buy>> buysFromSessions
   ) {
      System.out.println("Price Difference for Purchased Product by Session");

      // Gets all the sessions that involve purchases (BUY statements.)
      List<String> sessionsWithPurchases = getSessionsWithPurchases(
              sessionsFromCustomer, buysFromSessions);

      // Gets a map of all BUY sessions and their respective average price.
      Map<String, Double> avgPriceViewedSessions = findAveragePriceViewedAllSessions(
              sessionsWithPurchases, viewsFromSessions
      );

      // Prints the current session, then prints out all purchases minus the avgPrice of all respective purchases.
      for (String session : sessionsWithPurchases) {
         System.out.println(session);
         for (Buy purchase : buysFromSessions.get(session)) {
            System.out.printf("\t%s %.1f\n", purchase.getProduct(),
                    purchase.getPrice() - avgPriceViewedSessions.get(session));
         }
      }

   }

   // Calculates the average prices of all items viewed in every session with purchases.
   private static Map<String, Double> findAveragePriceViewedAllSessions(
           List<String> sessionsWithPurchases, Map<String, List<View>> viewsFromSessions
   ) {
      // Initializes the list of doubles (averages) we're going to return.
      Map<String, Double> averageMap = new HashMap<>();

      // Calculates the average price of viewed items in each session.
      for (String session : sessionsWithPurchases) {
         int viewPriceTot = 0;
         int viewedItems = 0;
         for (View viewItem : viewsFromSessions.get(session)) {
            viewPriceTot += viewItem.getPrice();
            viewedItems += 1;
         }
         averageMap.put(session, ((double) viewPriceTot) / viewedItems);
      }

      return averageMap;
   }

   //write this after you have figured out how to store your data
   //make sure that you understand the problem
   private static void printCustomerItemViewsForPurchase(
           Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<View>> viewsFromSessions,
           Map<String, List<Buy>> buysFromSessions
   )
   {
      System.out.println("Number of Views for Purchased Product by Customer");

      // Encapsulates all sessions with purchases.
      List<String> sessionPurchaseMadeList = getSessionsWithPurchases(
              sessionsFromCustomer, buysFromSessions);

      // Maps customers to sessions with purchases.
      Map<String, List<String>> customerMapSessionPurchased = mapCustomerProductsPurchased(
              sessionsFromCustomer, sessionPurchaseMadeList);


      // For every customer, we will find all the items purchased,
      // then calculate how many sessions these items were found in.
      for (Map.Entry<String, List<String>> entry: customerMapSessionPurchased.entrySet()) {
        List<String> productList1 = getItemsPurchasedByCustomer(entry.getValue(), buysFromSessions);
         if (productList1.size() >= 1) {


        
         System.out.println(entry.getKey());

         List<String> productList = getItemsPurchasedByCustomer(entry.getValue(), buysFromSessions);
         for (String product: productList) {
            int totTimesViewed = 0;
            // Iterate through all sessions involving views, but only check if the current product is viewed
            // only if it pertains to the customer in question.
            for (Map.Entry<String, List<View>> entryView: viewsFromSessions.entrySet()) {
               boolean hasProduct = false;
               if (sessionsFromCustomer.get(entry.getKey()).contains(entryView.getKey())) {
                  for (View viewItem: entryView.getValue()) {
                     if (viewItem.getProduct().equals(product)) {
                        hasProduct = true;
                        break;
                     }
                  }
               }
               if (hasProduct) {
                  totTimesViewed += 1;
               }
            }
            System.out.printf("\t%s %d\n", product, totTimesViewed);
         }
      }
}


   }


   // Gets all items purchased by a customer.
   private static List<String> getItemsPurchasedByCustomer(
           List<String> sessionListForCustomer,
           Map<String, List<Buy>> buysFromSessions
   ){
      // Initializes list of products.
      List<String> listProduct = new LinkedList<>();

      // Goes through every session in the session list
      // and adds every product that was bought within those
      // sessions to listProduct.
      for (String session: sessionListForCustomer) {
         for (Buy purchase: buysFromSessions.get(session)) {
            if (!listProduct.contains(purchase)) {
               listProduct.add(purchase.getProduct());
            }
         }
      }
      return listProduct;
   }




   // Maps customers to sessions with products purchased.
   private static Map<String, List<String>> mapCustomerProductsPurchased(
           Map<String, List<String>> sessionsFromCustomers,
           List<String> sessionsWithPurchases
   ){
      Map<String, List<String>> newCustomerMap = new HashMap<>();
      // Initializes new map.
      for (String customer: sessionsFromCustomers.keySet()) {
         newCustomerMap.put(customer, new LinkedList<>());
      }

      for (Map.Entry<String, List<String>> customer: sessionsFromCustomers.entrySet()) {
         for (String session: sessionsWithPurchases) {
            if (customer.getValue().contains(session)) {
               newCustomerMap.get(customer.getKey()).add(session);
            }
         }

      }
      return newCustomerMap;
   }
   // Finds all the sessions with products purchased.
   private static List<String> getSessionsWithPurchases(
           Map<String, List<String>> sessionsFromCustomer,
           Map<String, List<Buy>> buysFromSessions
   )
   {
      // Initializes the session list.
      List<String> sessionList = new LinkedList<>();

      // Loops over all possible sessions, and finds ones for which purchases exist.
      for (Map.Entry<String, List<String>> entry: sessionsFromCustomer.entrySet()) {

         for (String sessionId: entry.getValue()) {
            if (buysFromSessions.containsKey(sessionId)) {
               sessionList.add(sessionId);
            }
         }

      }
      return sessionList;
   }

      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printStatistics(
      Map<String, List<String>> sessionsFromCustomer,
      Map<String, List<View>> viewsFromSessions,
      Map<String, List<Buy>> buysFromSessions
      /* add parameters as needed */
      )
   {

      printAverageViewsWithoutPurchase(sessionsFromCustomer,
              viewsFromSessions,
              buysFromSessions);

      printSessionPriceDifference(sessionsFromCustomer,
              viewsFromSessions,
              buysFromSessions);
      printCustomerItemViewsForPurchase(sessionsFromCustomer,
              viewsFromSessions,
              buysFromSessions);

      /* This is commented out as it will not work until you read
         in your data to appropriate data structures, but is included
         to help guide your work - it is an example of printing the
         data once propogated 
         printOutExample(sessionsFromCustomer, viewsFromSession, buysFromSession);
      */
   }

   /* provided as an example of a method that might traverse your
      collections of data once they are written 
      commented out as the classes do not exist yet - write them! */
//   private static void printOutExample(
//      final Map<String, List<String>> sessionsFromCustomer,
//      final Map<String, List<View>> viewsFromSession,
//      final Map<String, List<Buy>> buysFromSession) 
//   {
//      //for each customer, get their sessions
//      //for each session compute views
//      for(Map.Entry<String, List<String>> entry: 
//         sessionsFromCustomer.entrySet()) 
//      {
//         System.out.println(entry.getKey());
//         List<String> sessions = entry.getValue();
//         for(String sessionID : sessions)
//         {
//            System.out.println("\tin " + sessionID);
//            List<View> theViews = viewsFromSession.get(sessionID);
//            if (theViews != null) {
//               for (View thisView: theViews)
//               {
//                  System.out.println("\t\tviewed " + thisView.getProduct());
//               }
//            }
//
//         }
//      }
//   }


   private static String getFilename(String[] args)
   {
      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      return args[0];
   }


}
