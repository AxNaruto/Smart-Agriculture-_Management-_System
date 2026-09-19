-- ===================================================================
-- SMART AGRICULTURE MANAGEMENT SYSTEM - SEED DATA
-- ===================================================================

-- Clear existing reference data for clean re-runs
DELETE FROM crops;
DELETE FROM pest_advices;

-- 1. CROPS & NUTRIENT PROFILES (kg / acre)
INSERT INTO crops (name, soil_type, base_n, base_p, base_k, description) VALUES
('Rice', 'Alluvial', 40.0, 20.0, 20.0, 'Staple cereal; thrives in moisture-retentive fertile alluvial soils'),
('Rice', 'Clay', 38.0, 18.0, 20.0, 'Clay holds water well; reduce basal nitrogen slightly'),
('Rice', 'Black', 36.0, 20.0, 22.0, 'High nutrient capacity soil; balanced NPK required'),
('Rice', 'Red', 44.0, 22.0, 20.0, 'Needs slightly higher N due to faster percolation'),
('Rice', 'Sandy', 50.0, 25.0, 25.0, 'High leaching; split doses of nitrogen essential'),

('Wheat', 'Alluvial', 50.0, 25.0, 16.0, 'Ideal for Indo-Gangetic alluvial plains'),
('Wheat', 'Black', 45.0, 22.0, 16.0, 'Good moisture retention; optimal for central India'),
('Wheat', 'Clay', 48.0, 24.0, 18.0, 'Clayey loam suited for winter wheat'),
('Wheat', 'Red', 52.0, 26.0, 18.0, 'Requires phosphorus enrichment'),
('Wheat', 'Sandy', 60.0, 30.0, 20.0, 'Requires frequent light irrigation and split nitrogen'),

('Cotton', 'Black', 45.0, 22.0, 22.0, 'Black cotton soil (Regur) is ideal for fiber development'),
('Cotton', 'Alluvial', 48.0, 24.0, 24.0, 'Grown in northern irrigated cotton belt'),
('Cotton', 'Red', 52.0, 25.0, 24.0, 'Requires adequate micronutrients and potassium'),
('Cotton', 'Clay', 46.0, 22.0, 20.0, 'Ensure good drainage to prevent root rot'),
('Cotton', 'Sandy', 55.0, 28.0, 26.0, 'Not ideal; requires frequent organic compost and NPK'),

('Maize', 'Alluvial', 48.0, 24.0, 20.0, 'High yielding fodder and grain crop'),
('Maize', 'Red', 52.0, 26.0, 22.0, 'Requires proper zinc and nitrogen top dressing'),
('Maize', 'Black', 46.0, 22.0, 20.0, 'Deep black soils support high vegetative biomass'),
('Maize', 'Clay', 45.0, 24.0, 20.0, 'Avoid water stagnation at germination stage'),
('Maize', 'Sandy', 56.0, 28.0, 24.0, 'Split nitrogen into 3 equal vegetative applications'),

('Sugarcane', 'Alluvial', 100.0, 40.0, 48.0, 'Heavy feeder crop; requires sustained nutrient supply'),
('Sugarcane', 'Black', 95.0, 38.0, 50.0, 'Heavy black soils yield high sucrose content'),
('Sugarcane', 'Clay', 92.0, 40.0, 48.0, 'Ensure deep furrowing and proper drainage'),
('Sugarcane', 'Red', 105.0, 42.0, 50.0, 'Requires organic manuring along with chemical fertilizer'),
('Sugarcane', 'Sandy', 115.0, 45.0, 55.0, 'Requires fertigation and mulch to prevent nutrient leaching');

-- 2. SAMPLE MANDI MARKETPLACE LISTINGS
INSERT INTO mandi_listings (farmer_name, crop_name, quantity_quintals, price, location, phone, listed_date) VALUES
('Rameshwar Patel', 'Wheat (Sharbati)', 85.0, 2450.0, 'Sehore Mandi, MP', '9876543210', '2026-03-10'),
('Harpreet Singh', 'Rice (Basmati 1121)', 120.0, 3850.0, 'Karnal Mandi, Haryana', '9812345678', '2026-03-12'),
('Venkat Reddy', 'Cotton (Medium Staple)', 45.0, 7100.0, 'Warangal Mandi, Telangana', '9701234567', '2026-03-14'),
('Suresh Deshmukh', 'Sugarcane', 350.0, 340.0, 'Kolhapur Mandi, Maharashtra', '9422012345', '2026-03-15'),
('Balram Yadav', 'Maize (Yellow Corn)', 60.0, 2150.0, 'Chhindwara Mandi, MP', '9988776655', '2026-03-16');

-- 3. PEST ADVISORY (Crop, Symptom, Biological Remedy, Chemical Dosage)
INSERT INTO pest_advices (crop_name, symptom, bio_remedy, chemical_dosage) VALUES
('Rice', 'Spindle-shaped brown spots on leaves with grey center (Leaf Blast)', 
 'Spray Pseudomonas fluorescens @ 10g/liter of water or apply Neem cake @ 150 kg/acre to soil.', 
 'Foliar spray of Tricyclazole 75% WP @ 0.6 g/liter or Carbendazim 50% WP @ 1.0 g/liter of water.'),

('Rice', 'Yellowing of leaf tips drying downwards and dead hearts in stem (Stem Borer)', 
 'Install Pheromone traps @ 5 traps/acre; release egg parasitoid Trichogramma japonicum @ 40,000/acre.', 
 'Broadcast Cartap Hydrochloride 4% G @ 10 kg/acre or Chlorantraniliprole 18.5% SC @ 60 ml/acre.'),

('Wheat', 'Orange-brown powdery pustules scattered on leaf blades (Brown / Leaf Rust)', 
 'Apply diluted cow urine spray (1:10 ratio) mixed with sour buttermilk, or spray Trichoderma viride.', 
 'Foliar spray of Propiconazole 25% EC (Tilt) @ 1 ml/liter (200 ml/acre in 200 liters water).'),

('Wheat', 'Termite damage cutting roots and wilting tillers in dry soil', 
 'Apply well-decomposed FYM treated with Metarhizium anisopliae bio-fungus @ 2 kg/acre.', 
 'Treat seeds with Chlorpyrifos 20% EC @ 4 ml/kg seed before sowing or soil drenching @ 1.2 L/acre.'),

('Cotton', 'Upward curling of leaves, yellow margins and sticky honeydew (Whitefly / Aphids)', 
 'Erect yellow sticky traps @ 8-10 traps/acre; spray 5% Neem Seed Kernel Extract (NSKE).', 
 'Spray Flonicamid 50% WG @ 80 g/acre or Diafenthiuron 50% WP @ 250 g/acre in 200 L water.'),

('Cotton', 'Bore holes in bolls plugged with excreta and flared squares (Bollworm)', 
 'Install Helicoverpa pheromone traps @ 5/acre; spray Bacillus thuringiensis (Bt formulation) @ 400g/acre.', 
 'Spray Emamectin Benzoate 5% SG @ 88 g/acre or Spinosad 45% SC @ 75 ml/acre.'),

('Maize', 'Ragged shot holes on whorl leaves with sawdust-like frass (Fall Armyworm - FAW)', 
 'Handpick egg masses; apply sand + lime mixture (9:1) into whorls; release Trichogramma pretiosum.', 
 'Apply Chlorantraniliprole 18.5% SC @ 0.4 ml/liter directly into the leaf whorls.'),

('Sugarcane', 'Drying of central shoot forming dead hearts that emit foul odor (Early Shoot Borer)', 
 'Trash mulching @ 3 tonnes/acre at 3-5 days after planting; release Trichogramma chilonis.', 
 'Soil application of Fipronil 0.3% G @ 10 kg/acre or spray Chlorantraniliprole 18.5% SC @ 150 ml/acre.');
