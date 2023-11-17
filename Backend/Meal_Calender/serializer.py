from rest_framework import serializers
from Meal_Calender.models import McalenderTb

class MealCalender_serializer(serializers.ModelSerializer):
    class Meta:
        model = McalenderTb
        fields = '__all__'