from rest_framework import serializers
from Feedback.models import FeedbackTb
from Feedback.models import TechTb


class Feedback_serializer(serializers.ModelSerializer):
    class Meta:
        model = FeedbackTb
        fields = '__all__'

class Tech_serializer(serializers.ModelSerializer):
    class Meta:
        model = TechTb
        fields = '__all__'