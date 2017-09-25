package com.i.englishbook.model;

import com.i.englishbook.annotations.Column;
import com.i.englishbook.annotations.Table;

/**
 * Created by huytran on 8/17/2017.
 */

@Table(Name = "Sentence")
public class Sentence {
    @Column(Name = "Id", Type = DBType.INTEGER)
    public int Id;
    @Column(Name = "CateId", Type = DBType.INTEGER)
    public int CateId;
    @Column(Name = "e")
    public String E;
    @Column(Name = "v")
    public String V;
}
