# JSON files:
## Events:
- id (int)
- title (String)
- userid (int)
- date (String in ISO_Local_Date format)
- starttime (String in ISO_Local_Time format)
- endtime (String in ISO_Local_Time format)
- location (String) 
- description (String)
- label (String)
- pinned (boolean)
- sub-events (JSONArray of int)

## Notes:
- id (int)
- title (String)
- userid (int)
- location (String)
- description (String)
- label (String)
- pinned (boolean)
- sub-notes (JSONArray of int)

## Users:
- id (int)
- username (String)
- password (String)
- gcal-credential (String)