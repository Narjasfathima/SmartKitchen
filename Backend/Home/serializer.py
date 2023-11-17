from rest_framework import serializers
from Home.models import LoginTb

class Login_serializer(serializers.ModelSerializer):
    class Meta:
        model = LoginTb
        fields = '__all__'