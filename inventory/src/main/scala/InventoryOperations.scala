package main.scala
import scala.concurrent.Future


/**
  * Created by knoldus on 31/7/17.
  */
trait InventoryOperations {

  def filterItems(inventory: Inventory, category: String, itemName: String,
                  filterParameter: String): Future[List[Item]] = {
    Future {
      filterParameter.toLowerCase() match {

        case "price low to high" => inventory.listOfItems.filter(_.category == category)
          .sortBy(_.price.priceValue)

        case "price high to low" => inventory.listOfItems.filter(_.category == category)
          .sortBy(_.price.priceValue).reverse

        case "default" => inventory.listOfItems.filter(_.category == category)
      }
    }
  }

  def returnPrice(inventory: Inventory, itemID: Int): Future[Price] = Future{
    inventory.listOfItems.filter(_.itemID == itemID)(0).price
  }

  def updateCount(inventory: Inventory, itemID: Int, updateBy: Int)(f: (Int, Int) => Int): Future[Option[Item]] = {
    Future {
      val itemWithOldCount = inventory.listOfItems.filter(_.itemId == itemID)(0)
      if (updateBy == 0) {
        val itemNewCount = itemWithOldCount.copy(itemCount = f(itemWithOldCount.itemCount, updateBy))
        Some(itemNewCount)
      }
      else None
    }
  }


  def addItemstoInventory(item : Item , inventory: Inventory): (Inventory, Int) ={

    val newListOfItems  =    item :: inventory.listOfItems
    val newInventory = Inventory(newListOfItems)

    (newInventory, item.itemId)
  }

}
