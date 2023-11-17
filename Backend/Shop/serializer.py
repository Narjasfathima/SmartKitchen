from rest_framework import serializers
from Shop.models import ShopTb

class Shop_serializer(serializers.ModelSerializer):
    class Meta:
        model = ShopTb
        fields = '__all__'