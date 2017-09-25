<?xml version="1.0" encoding="UTF-8"?>
<sch:schema xmlns:sch="http://purl.oclc.org/dsdl/schematron" queryBinding="xslt2"
    xmlns:sqf="http://www.schematron-quickfix.com/validator/process">
    <sch:ns uri="http://toto" prefix="toto"/>
    
    <sch:pattern >
        <sch:rule context="toto:toto">
            <sch:assert test="local-name(*[1]) = 'foo'">toto element must start with a foo element</sch:assert>
        </sch:rule>
    </sch:pattern>    
</sch:schema>