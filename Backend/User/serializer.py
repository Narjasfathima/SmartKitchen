from rest_framework import serializers
from User.models import UserTb

class User_serializer(serializers.ModelSerializer):
    class Meta:
        model = UserTb
        fields = '__all__'