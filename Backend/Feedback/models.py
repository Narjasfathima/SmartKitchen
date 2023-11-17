from django.db import models
from Home.models import LoginTb
# Create your models here.
class FeedbackTb(models.Model):
    fid = models.AutoField(primary_key=True)
    # lid = models.IntegerField()
    lid = models.ForeignKey(LoginTb, to_field='lid', on_delete=models.CASCADE)
    feed = models.CharField(max_length=50)
    fdate = models.DateField()
    ftime = models.TimeField()
    fstatus = models.CharField(max_length=25)

    class Meta:
        managed = False
        db_table = 'feedback_tb'


class TechTb(models.Model):
    teid = models.AutoField(primary_key=True)
    detail = models.CharField(max_length=500)
    tdate = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'tech_tb'
