import yaml

from item import Item
from shopping_cart import ShoppingCart
from errors import TooManyMatchesError
from errors import ItemNotExistError
from errors import ItemAlreadyExistsError

class Store:
    def __init__(self, path):
        with open(path) as inventory:
            items_raw = yaml.load(inventory, Loader=yaml.FullLoader)['items']
        self._items = self._convert_to_item_objects(items_raw)
        self._shopping_cart = ShoppingCart()

    @staticmethod
    def _convert_to_item_objects(items_raw):
        return [Item(item['name'],
                     int(item['price']),
                     item['hashtags'],
                     item['description'])
                for item in items_raw]

    def get_items(self) -> list:
        return self._items

    def search_by_name(self, item_name: str) -> list:            
        listWithCounters = []
        for i in self._items:
            if (item_name in i.name):
                listWithCounters.append((0,i))
        for index, (counter, item) in enumerate(listWithCounters):
          counter_increment = sum(1 for i in self._shopping_cart.items for h in item.hashtags if h in i.hashtags)
          listWithCounters[index] = (counter + counter_increment, item)    
        listWithCounters.sort(key=lambda x: (-x[0], x[1].name))
        returnList = []
        for (j,item) in listWithCounters:
            if item not in self._shopping_cart.items:
               returnList.append(item)
        return returnList    

    def search_by_hashtag(self, hashtag: str) -> list:
        listWithCounters = []
        for i in self._items:
            if (hashtag in i.hashtags):
                listWithCounters.append((0,i))
        for index, (counter, item) in enumerate(listWithCounters):
          counter_increment = sum(1 for i in self._shopping_cart.items for h in item.hashtags if h in i.hashtags)
          listWithCounters[index] = (counter + counter_increment, item)
        listWithCounters.sort(key=lambda x: (-x[0], x[1].name))
        returnList = []
        for (j,item) in listWithCounters:
            if item not in self._shopping_cart.items:
               returnList.append(item)
        return returnList  

    def add_item(self, item_name: str):
        countFound = 0
        for i in self._items:
            if item_name in i.name:
                countFound += 1
        if countFound == 0:
            raise ItemNotExistError(f"Item '{item_name}' does not exist in the cart.")
            return
        if countFound > 1:
            raise TooManyMatchesError(f"Too many matches for name'{item_name}'")
            return
        for i in self._items:
            if item_name in i.name:
                theItem = i
        if theItem in self._shopping_cart.items:
                raise ItemAlreadyExistsError(f"Item '{item_name}' already exists in the cart.")
                return
        self._shopping_cart.add_item(theItem)
        

    def remove_item(self, item_name: str):
        countFound = 0
        theItem = None
        for i in self._shopping_cart.items:
            if item_name in i.name:
                countFound += 1
        if countFound == 0:
            raise ItemNotExistError(f"Item '{item_name}' does not exist in the cart.")
            return
        if countFound > 1:
            raise TooManyMatchesError(f"Too many matches for name'{item_name}'")
            return
        for i in self._shopping_cart.items:
            if item_name in i.name:
                theItem = i
        self._shopping_cart.remove_item(theItem.name)

    def checkout(self) -> int:
        return self._shopping_cart.get_subtotal()
