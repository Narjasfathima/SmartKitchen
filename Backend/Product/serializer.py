from rest_framework import serializers
from Product.models import ProductTb

class Product_serializer(serializers.ModelSerializer):
    class Meta:
        model = ProductTb
        fields = '__all__'