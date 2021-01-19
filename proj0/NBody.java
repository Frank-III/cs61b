public class NBody{
	public static double readRadius(String address){
		In in = new In(address);
		in.readInt();
		double radius  = in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String address){
		In in = new In(address);
		Int num = in.readInt();
		in.readDouble();
		Planet[] p = new Planet[num];
		for(int i = 0; i < num ; i = i + 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			p[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return p;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.enableDoubleBuffering();
		double t = 0;
		while(t != T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i< planets.length; i = i + 1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < planets.length; i = i +1 ) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			for (Planet planet : planets){
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);	
			t = t + dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}
