from item import Item
from errors import ItemAlreadyExistsError
from errors import ItemNotExistError

class ShoppingCart:
    def __init__(self):
        self.items = []

    def add_item(self, item: Item):
        for i in self.items:
            if (i.name == item.name):
                raise ItemAlreadyExistsError(f"Item '{item.name}' already exists in the cart.")
        self.items.append(item)
    

    def remove_item(self, item_name: str):
        exists = False
        for i in self.items:
            if (item_name == i.name):
                exists = True
                self.items.remove(i)
        if (exists == False):
            raise ItemNotExistError(f"Item '{item_name}' does not exist in the cart.")
            

    def get_subtotal(self) -> int:
        subToTalSum = 0
        for i in self.items:
            subToTalSum += i.price
        return subToTalSum
