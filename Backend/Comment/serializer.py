from rest_framework import serializers
from Comment.models import CookVideoTb
from Comment.models import CommentTb

class Comment_serializer(serializers.ModelSerializer):
    class Meta:
        model = CommentTb
        fields = '__all__'


class CookVideo_serializer(serializers.ModelSerializer):
    class Meta:
        model = CookVideoTb
        fields = '__all__'