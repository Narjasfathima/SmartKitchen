from django.db import models
from Home.models import LoginTb
# Create your models here.
class UserTb(models.Model):
    uid = models.AutoField(primary_key=True)
    # lid = models.IntegerField()
    lid= models.ForeignKey(LoginTb, to_field='lid', on_delete=models.CASCADE)
    uname = models.CharField(max_length=25)
    ugender = models.CharField(max_length=10)
    uplace = models.CharField(max_length=50)
    homename = models.CharField(max_length=50)
    upin = models.IntegerField()
    upost = models.CharField(max_length=25)
    uemail = models.CharField(max_length=25)
    uphno = models.BigIntegerField()

    class Meta:
        managed = False
        db_table = 'user_tb'
