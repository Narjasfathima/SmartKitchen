from django.db import models
from User.models import UserTb
from Home.models import LoginTb
# Create your models here.
class CookVideoTb(models.Model):
    cid = models.AutoField(primary_key=True)
    # uid = models.IntegerField()
    uid = models.ForeignKey(LoginTb, to_field='lid', on_delete=models.CASCADE)
    cname = models.CharField(max_length=50)
    video = models.CharField(max_length=5000)
    cdate = models.DateField()
    ctime = models.TimeField()

    class Meta:
        managed = False
        db_table = 'cook_video_tb'

class CommentTb(models.Model):
    comid = models.AutoField(primary_key=True)
    # uid = models.IntegerField()
    uid = models.ForeignKey(LoginTb, to_field='lid', on_delete=models.CASCADE)
    # cid = models.IntegerField()
    cid = models.ForeignKey(CookVideoTb, to_field='cid', on_delete=models.CASCADE)
    com = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'comment_tb'


