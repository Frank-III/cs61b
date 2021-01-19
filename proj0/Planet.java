public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p){
		double delta_x = xxPos - p.xxPos;
		double delta_y = yyPos - p.yyPos;
		double distanceXY = delta_x*delta_x + delta_y*delta_y;
		return Math.sqrt(distanceXY);
	} 
	public double calcForceExertedBy(Planet p){
		double r2 = calcDistance(p) * calcDistance(p);
		double Abs_F = G*mass*p.mass/r2;
		return Abs_F;
	}
	public double calcForceExertedByX(Planet p){
		double x_force = calcForceExertedBy(p)*(p.xxPos - xxPos)/calcDistance(p);
		return x_force;
	} 
	public double calcForceExertedByY(Planet p){
		double y_force = calcForceExertedBy(p)*(p.yyPos - yyPos)/calcDistance(p);
		return y_force;
	}	
	public double calcNetForceExertedByX(Planet[] p){
		double sum_xnet = 0.0;
		for(int i = 0; i < p.length; i = i + 1){
			if(this.equals(p[i])){
				continue;
			}
			sum_xnet = sum_xnet + calcForceExertedByX(p[i]);
		}	
		return sum_xnet;
	}	
	public double calcNetForceExertedByY(Planet[] p){
		double sum_ynet = 0.0;
		for(int i = 0; i < p.length; i = i + 1){
			if(this.equals(p[i])){
				continue;
			}
				sum_ynet = sum_ynet + calcForceExertedByY(p[i]);
		}
		return sum_ynet;
	}
	public void update(double dt, double fX, double fY){
		double xacc = fX/mass;
		double yacc = fY/mass;
		xxVel = xxVel + dt*xacc;
		yyVel = yyVel + dt*yacc;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
	}
}

