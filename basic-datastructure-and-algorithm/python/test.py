import time
from datetime import date, timedelta

import MySQLdb

def fetch_free_order_stats():
    today = date.today()
    yesterday = today - timedelta(days=1)
    yesterday_start_ts = int(time.mktime(time.strptime(str(yesterday), '%Y-%m-%d')))
    today_start_ts = int(time.mktime(time.strptime(str(today), '%Y-%m-%d')))
    now = int(time.time())

    yesterday_stats = _fetch_free_order_stats(yesterday_start_ts, today_start_ts)
    today_stats = _fetch_free_order_stats(today_start_ts, now)

    #同比 截至到昨天相同时间的统计
    yesterday_same_time_stats = _fetch_free_order_stats(yesterday_start_ts, now-86400)

    return today_stats, yesterday_same_time_stats, yesterday_stats


def _fetch_free_order_stats(start_ts, end_ts):
    results = {
        'total': 0,
    }
    db = MySQLdb.connect(host="10.3.20.208", port=3967, user="common_traded_r", passwd="iy3gvkWF0MQtMJC_kNtfgQxPshx6OqLR", db='common_tradedb')
    sql = """
        select goods_id_list , count(*) 
        from trade_order
        where aid = 13 and biz_id = 7
        and pay_fee = 0
        and create_time >= {start}
        and create_time <  {end}
        and status = 0
        and trade_type = 1
        group by goods_id_list
    """
    db.query(sql.format(start=start_ts, end=end_ts))
    r = db.store_result()
    _result = r.fetch_row()
    while len(_result) > 0:
        results[int(_result[0][0])] = _result[0][1]
        results["total"] += _result[0][1]
        _result = r.fetch_row()

    return results



if __name__ == "__main__":
    try:
        stats = fetch_free_order_stats()
    except Exception:
        stats = ({}, {}, {})
        print

    _free_order_stats_detail = u"""0元订单统计:
    今日实时 总量= {total1}
    昨日同比 总量= {total2}
    昨日全天 总量= {total3}
    今天实时 GID维度统计:
    gid,\tnumber_of_orders\n
            """.format(
        total1=stats[0].get("total", 0),
        total2=stats[1].get("total", 0),
        total3=stats[2].get("total", 0),
    )

    for k, v in stats[0].items():
        if k != "total":
            _free_order_stats_detail += "{gid},\t{number_of_orders}\n".format(k, v)

    print _free_order_stats_detail
