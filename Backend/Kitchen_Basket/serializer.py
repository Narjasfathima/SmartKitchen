from rest_framework import serializers
from Kitchen_Basket.models import KitchenBasketTb
from Kitchen_Basket.models import IngredientTb
from Kitchen_Basket.models import Temp

class KitchenBasket_serializer(serializers.ModelSerializer):
    class Meta:
        model = KitchenBasketTb
        fields = '__all__'


class Ingredient_serializer(serializers.ModelSerializer):
    class Meta:
        model = IngredientTb
        fields = '__all__'


class Temp_serializer(serializers.ModelSerializer):
    class Meta:
        model = Temp
        fields = '__all__'