from django.db import models
from User.models import LoginTb

# Create your models here.
class McalenderTb(models.Model):
    mcid = models.AutoField(primary_key=True)
    # uid = models.IntegerField()
    uid = models.ForeignKey(LoginTb, to_field='lid', on_delete=models.CASCADE)
    mcdate = models.DateField()
    mctime = models.CharField(max_length=20, blank=True, null=True)
    mcbreak = models.CharField(max_length=50, blank=True, null=True)
    mclunch = models.CharField(max_length=50, blank=True, null=True)
    mcdinner = models.CharField(max_length=50, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'mcalender_tb'
