from rest_framework import serializers
from Order_Manage.models import BookProductTb
from Order_Manage.models import OrderProductTb

class BookProduct_serializer(serializers.ModelSerializer):
    class Meta:
        model = BookProductTb
        fields = '__all__'

class OrderProduct_serializer(serializers.ModelSerializer):
    class Meta:
        model = OrderProductTb
        fields = '__all__'